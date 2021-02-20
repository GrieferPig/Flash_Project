package com.grieferpig.flash;

import android.annotation.SuppressLint;
import android.app.ApplicationErrorReport;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter {
    private List<Light> lightList;
    public ListAdapter(List<Light> lights){
        this.lightList = lights;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView picture;
        private TextView title,desc;
        private CardView card;
        public ViewHolder(View itemView) {
            super(itemView);
            picture = (ImageView) itemView.findViewById(R.id.item_Image);
            title = (TextView) itemView.findViewById(R.id.item_Title);
            desc = (TextView) itemView.findViewById(R.id.item_Desc);
            card = (CardView) itemView.findViewById(R.id.item_Card);
        }

        public ImageView getPicture(){
            return picture;
        }

        public TextView getTitle(){
            return title;
        }

        public TextView getDesc(){
            return desc;
        }

        public CardView getCard() {
            return card;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,null));
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder vh = (ViewHolder) holder;
        vh.getPicture().setImageResource(lightList.get(position)
                .getImgsrc());
        vh.getTitle().setText(lightList.get(position).getTitle());
        vh.getDesc().setText(lightList.get(position).getDesc());
        vh.getCard().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            double counter = 1;
                            while(counter > 0.85){
                                counter = counter - 0.01;
                                v.setScaleX((float) counter);
                                v.setScaleY((float) counter);
                                v.setAlpha((float) counter);
                                try {
                                    sleep(4);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            while(counter < 1){
                                counter = counter + 0.01;
                                v.setScaleX((float) counter);
                                v.setScaleY((float) counter);
                                v.setAlpha((float) counter);
                                try {
                                    sleep(4);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }.start();
                mOnItemClickListener.onItemClick(position);
            }
        });

    }

    @Override public int getItemCount() {
        return lightList.size();
    }
    /** * ItemClick的回调接口 */
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
