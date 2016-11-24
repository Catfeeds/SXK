package com.example.cfwifine.sxk.Section.PublishNC.View;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by cfwifine on 16/11/23.
 */

public class RecycleViewListener implements RecyclerView.OnItemTouchListener {

        private OnItemClickListener clickListener;
        private GestureDetector gestureDetector;

        public interface OnItemClickListener {
            void onItemClick(View view, int position);

            void onItemLongClick(View view, int position);
        }

        public RecycleViewListener(final RecyclerView recyclerView,
                                       OnItemClickListener listener) {
            this.clickListener = listener;
            gestureDetector = new GestureDetector(recyclerView.getContext(),
                    new GestureDetector.SimpleOnGestureListener() {
                        @Override
                        public boolean onSingleTapUp(MotionEvent e) {
                            View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                            if (childView != null && clickListener != null) {
                                clickListener.onItemClick(childView, recyclerView.getChildPosition(childView));
                            }
                            return true;
                        }

                        @Override
                        public void onLongPress(MotionEvent e) {
                            View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                            if (childView != null && clickListener != null) {
                                clickListener.onItemLongClick(childView, recyclerView.getChildPosition(childView));
                            }
                        }
                    });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            gestureDetector.onTouchEvent(e);
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }


        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }