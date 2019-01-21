package com.example.danieloneill.healthyhabits;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import java.util.List;

public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.VideoViewHolder> {

    List<YoutubeVideos> youtubeVideosList;

    public YoutubeVideoAdapter() {
    }

    public YoutubeVideoAdapter(List<YoutubeVideos> youtubeVideosList) {
        this.youtubeVideosList = youtubeVideosList;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R. layout.video_beginner, parent, false);

        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {

        holder.videoWeb.loadData(youtubeVideosList.get(position).getVideoUrl(), "text/html", "utf-8");

    }

    @Override
    public int getItemCount() { return youtubeVideosList.size(); }

    public class VideoViewHolder extends RecyclerView.ViewHolder{

        WebView videoWeb;

        public VideoViewHolder(View itemView) {
            super(itemView);

            videoWeb = (WebView) itemView.findViewById(R.id.videoWebView);

            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebChromeClient(new  WebChromeClient() {


            } );
        }
    }
}
