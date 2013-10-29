package com.example.usabilityapp;
import java.util.ArrayList;
import Logic.ExerciseLogic.Exercise;
import Logic.Workout.Workout;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class CustomWorkoutListAdapter extends BaseAdapter{
	private ArrayList<Workout> listData;
	private LayoutInflater layoutInflater;
	
	public CustomWorkoutListAdapter(Context context, ArrayList<Workout> listData){
		this.listData = listData;
		layoutInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount(){
		return listData.size();
	}
	
	@Override
	public Object getItem(int position){
		return listData.get(position);
	}
	
	@Override
	public long getItemId(int position){
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent){
		ViewHolder holder;
		if(convertView == null){
			convertView = layoutInflater.inflate(R.layout.workout_list_row_layout,null);
			holder = new ViewHolder();
			holder.nameView = (TextView)convertView.findViewById(R.id.workoutNameCol);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		holder.nameView.setText(listData.get(position).getName());
		return convertView;
	}
	
	static class ViewHolder{
		TextView nameView;
	}
}
 