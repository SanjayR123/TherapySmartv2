package android.example.therapysmart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import data.Med;
import data.MedRepository;

public class MedCursorAdapter extends RecyclerView.Adapter<MedCursorAdapter.MedViewHolder> {


    private final ArrayList<Med> mMed;
    private final onMedListener mOnMedListener;

    MedCursorAdapter(ArrayList<Med> mMed, onMedListener onMedListener) {
        this.mMed = mMed;
        this.mOnMedListener = onMedListener;
    }

    @NonNull
    @Override
    public MedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MedViewHolder(view, mOnMedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MedCursorAdapter.MedViewHolder holder, int position) {

        try{
            holder.nameTextView.setText(mMed.get(position).getName());

        }catch (NullPointerException e){
            Log.e("Why", "onBindViewHolder: Null Pointer: " + e.getMessage() );
        }
        //Log.d("TaskCursor","task is " + mTasks.get(position));

    }


    @Override
    public int getItemCount() {
        return mMed.size();
    }

    Context context;

    public void deleteMed(Med med) {
        mMed.remove(med);
        MedRepository mMedRepository = new MedRepository(context);
        mMedRepository.deleteMed(med);
    }


    public static class MedViewHolder extends RecyclerView.ViewHolder implements
            View.OnTouchListener,
            GestureDetector.OnGestureListener {

        TextView nameTextView;
        Button delete_button;
        onMedListener mOnMedListener;
        GestureDetector mGestureDetector;

        MedViewHolder(View itemView, onMedListener onMedListener) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            delete_button = itemView.findViewById(R.id.delete_button);
            mOnMedListener = onMedListener;

            mGestureDetector = new GestureDetector(itemView.getContext(), this);
            itemView.setOnTouchListener(this);

            delete_button.setOnClickListener(view -> mOnMedListener.onDeleteClick(getAdapterPosition()));

        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mGestureDetector.onTouchEvent(event);
            return true;
        }

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }
    }

    public interface onMedListener{
        void onMedClick(int position);
        void onDeleteClick (int position);
    }
}