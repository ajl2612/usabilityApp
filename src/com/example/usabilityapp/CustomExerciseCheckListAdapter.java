package com.example.usabilityapp;
import java.util.ArrayList;
import Logic.ExerciseLogic.Exercise;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;


public class CustomExerciseCheckListAdapter extends BaseAdapter{
	private ArrayList<Exercise> listData;
	private LayoutInflater layoutInflater;
	
	public CustomExerciseCheckListAdapter(Context context, ArrayList<Exercise> listData){
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
			convertView = layoutInflater.inflate(R.layout.exercise_check_list_row_layout,null);
			holder = new ViewHolder();
			holder.nameView = (CheckedTextView) convertView.findViewById(R.id.workoutExerciseNameCol);
			holder.typeView = (TextView) convertView.findViewById(R.id.typeCol);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}

		holder.nameView.setText(listData.get(position).getName());
		holder.typeView.setText(listData.get(position).getType());
		
		return convertView;
	}
	
	static class ViewHolder{
		CheckedTextView nameView;
		TextView typeView;
	}
}
 