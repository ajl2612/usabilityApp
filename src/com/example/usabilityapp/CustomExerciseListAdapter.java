package com.example.usabilityapp;
import java.util.ArrayList;

import Logic.Exercise.Exercise;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomExerciseListAdapter extends BaseAdapter{
	private ArrayList<Exercise> listData;
	private LayoutInflater layoutInflater;
	
	public CustomExerciseListAdapter(Context context, ArrayList<Exercise> listData){
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
			convertView = layoutInflater.inflate(R.layout.exercise_list_row_layout,null);
			holder = new ViewHolder();
			holder.iconView = (ImageView)convertView.findViewById(R.id.exerciseIcon); 
			holder.nameView = (TextView)convertView.findViewById(R.id.nameCol);
			holder.typeView = (TextView)convertView.findViewById(R.id.typeCol);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		holder.nameView.setText(listData.get(position).getName());
		holder.typeView.setText(listData.get(position).getType());
		
		String type = listData.get(position).getType();
		if( type.equals("Stretch") || type.equals("Body Weight")){
			holder.iconView.setImageResource(R.drawable.flex_icon);
		}
		else if(type.equals("Aerobic")){
			holder.iconView.setImageResource(R.drawable.endurance_icon);
		}
		
		return convertView;
	}
	
	static class ViewHolder{
		TextView idView;
		ImageView iconView;
		TextView nameView;
		TextView typeView;
	}
}
 