package com.stuhua.mvp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stuhua.mvp.R;
import com.stuhua.mvp.model.ZhihuContentJson;
import com.stuhua.mvp.ui.ZhihuAnswerActivity;

/**
 * Created by llh on 2016/9/2.
 */
public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentHolder> {
  public static final String sQUESTION_ANSWER_ID = "questionAnswerId";
  public static final String sUSER_AVATAR = "useravatar";
  public static final String sUSER_VOTE = "uservate";
  public static final String sQUESTION = "qustiontitle";
  public static final String sAUTHOR_NAME="author_name";

  private LayoutInflater mLayoutInflater;
  private Context mContext;
  private ZhihuContentJson mZhihuContentJson;
  private int mPostion;

  public ContentAdapter(Context context, ZhihuContentJson zhihuContentJson) {
    this.mContext = context;
    this.mZhihuContentJson = zhihuContentJson;
    mLayoutInflater = LayoutInflater.from(context);
  }

  @Override
  public ContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ContentHolder(mLayoutInflater.inflate(R.layout.item_recyclerview, parent, false));
  }

  @Override
  public void onBindViewHolder(ContentHolder holder, int position) {
    holder.title.setText(mZhihuContentJson.getAnswers().get(position).getTitle());
    holder.body.setText(mZhihuContentJson.getAnswers().get(position).getSummary());
    holder.vote.setText(mZhihuContentJson.getAnswers().get(position).getVote());
  }

  @Override
  public int getItemCount() {
    return mZhihuContentJson.getCount();
  }

  public class ContentHolder extends RecyclerView.ViewHolder {
    TextView title;
    TextView body;
    TextView vote;
    //    SimpleDraweeView avatar;
//    CardView answerCard;
    public ContentHolder(final View itemView) {
      super(itemView);
      title = (TextView) itemView.findViewById(R.id.title);
      body = (TextView) itemView.findViewById(R.id.body);
      vote = (TextView) itemView.findViewById(R.id.vote);
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent = new Intent(mContext, ZhihuAnswerActivity.class);
          intent.putExtra(sQUESTION_ANSWER_ID, mZhihuContentJson.getAnswers().get(getPosition()).getQuestionAnswerId());
          intent.putExtra(sUSER_AVATAR, mZhihuContentJson.getAnswers().get(getPosition()).getAvatar());
          intent.putExtra(sUSER_VOTE, mZhihuContentJson.getAnswers().get(getPosition()).getVote());
          intent.putExtra(sAUTHOR_NAME, mZhihuContentJson.getAnswers().get(getPosition()).getAuthorname());
          intent.putExtra(sQUESTION, mZhihuContentJson.getAnswers().get(getPosition()).getTitle());
          mContext.startActivity(intent);
        }
      });
    }
  }
}
