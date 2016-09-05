package com.stuhua.mvp.ui;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.stuhua.mvp.R;
import com.stuhua.mvp.adapter.ContentAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by llh on 2016/9/3.
 */
public class ZhihuAnswerActivity extends AppCompatActivity {
  public static final String URL_MAIN = "https://www.zhihu.com/";

  private Toolbar mToolbar;
  private TextView mAuthorNameTV,mVoteTV;
  private WebView mWebView;
  private Handler mHandler;
  private Document mDoc;

  private String mQuestionAnswerId;
  private String mContent;
  private String TAG = "ZhihuAnswerActivity:";
  private String mUserAvatar;
  private String mUerVote;
  private String mQuestionTitle;
  private String mAuthorName;

  public void init() {
    mQuestionAnswerId = getIntent().getStringExtra(ContentAdapter.sQUESTION_ANSWER_ID);
    mUserAvatar = getIntent().getStringExtra(ContentAdapter.sUSER_AVATAR);
    mUerVote = getIntent().getStringExtra(ContentAdapter.sUSER_VOTE);
    mQuestionTitle = getIntent().getStringExtra(ContentAdapter.sQUESTION);
    mAuthorName = getIntent().getStringExtra(ContentAdapter.sAUTHOR_NAME);
    mWebView = getViewById(R.id.web_view);

    mToolbar = getViewById(R.id.toolbar);
    mToolbar.setTitle(mQuestionTitle);
    mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
    // 设置toolbar支持actionbar
    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });

    mAuthorNameTV=getViewById(R.id.tv_author_name);
    mAuthorNameTV.setText(mAuthorName);
    mVoteTV=getViewById(R.id.tv_vote);
    mVoteTV.setText(mUerVote);
  }

  private void webViewInit() {
    mWebView.getSettings().setJavaScriptEnabled(false);
    mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
  }

  public <T extends View> T getViewById(int id) {
    return (T) findViewById(id);
  }

  private String getHtml(String body) {
    final StringBuilder sb = new StringBuilder();
    sb.append("<!DOCTYPE html>");
    sb.append("<html dir=\"ltr\" lang=\"zh\">");
    sb.append("<head><style>img{ max-width: 100%!important;height: auto!important; display: block; margin: 10px 0;}</style>");
    sb.append("<meta name=\"viewport\" content=\"width=100%; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;\" />");
    sb.append("<link rel=\"stylesheet\" href='file:///android_asset/style.css' type=\"text/css\" media=\"screen\" />");
    sb.append("</head>");
    sb.append("<body style=\"padding:0px 8px 8px 8px; word-wrap:break-word;\">");
    sb.append("<div class=\"body\">");
    sb.append(body);
    sb.append("</div>");
    sb.append("</body>");
    sb.append("</html>");
    return sb.toString();
  }

  private String getAnswerBody() {
    if (mDoc != null) {
      Elements content = mDoc.getElementsByClass("AnswerItem-content");
      if (content != null) {
        for (int i = 0; i < content.size(); i++) {
          Element element = content.get(i);
          Elements ans = element.getElementsByClass("RichText");
          if (ans.size() != 0) {
            return addPrefixOfUrl(imgReplace(ans.first().toString())); //替换img
          }
        }
      }
    }
    return "<div class=\"answer-status\" id=\"answer-status\">\n" +
      "<a class=\"zg-right\" data-tip=\"s$b$为什么回答会被建议修改？\" href=\"/question/24752645\"><i class=\"zg-icon zg-icon-question-mark\"></i></a>\n" +
      "<p>回答等待修改（已修改・评估中）：不规范转载</a></p>\n" +
      "<p class=\"note\">\n" +
      "\n" +
      "作者修改内容通过后，回答会重新显示。如果一周内未得到有效修改，回答会自动折叠。\n" +
      "\n" +
      "</p>\n" +
      "</div>";
  }

  /**
   * 将html中从默认的地址换成真正的imgUrl
   *
   * @param html
   * @return
   */
  private String imgReplace(String html) {
    Document doc = Jsoup.parse(html);
    Elements imgs = doc.getElementsByTag("img");
    for (int i = 0; i < imgs.size(); i++) {
      String imgUrl = imgs.get(i).attr("data-actualsrc");
      imgs.get(i).attr("src", imgUrl);
    }
    return doc.toString();
  }

  //将地址拼接完整
  private String addPrefixOfUrl(String html) {
    Document doc = Jsoup.parse(html);
    String prefix = "//link.zhihu.com";
    Elements urls = doc.getElementsByTag("a");
    for (int i = 0; i < urls.size(); i++) {
      String str = urls.get(i).attr("href");
      int m = str.indexOf(prefix);

      if (m == 0) {
        urls.get(i).attr("href", "https:" + str);
      }
    }
    return doc.toString();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_zhihu_answer);
    init();
    webViewInit();
    new WebLoadingThread().execute();

  }

  class WebLoadingThread extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... strings) {
      try {
        mDoc = Jsoup.connect(URL_MAIN+mQuestionAnswerId).get();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return getHtml(getAnswerBody());
    }

    @Override
    protected void onPostExecute(String s) {
      super.onPostExecute(s);
      mWebView.loadDataWithBaseURL("", s, "text/html", "utf-8", null);
    }
  }
}
