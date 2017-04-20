package com.example.qq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sgy on 2017/3/3.
 */

public class MyExpandableListViewAdapter extends BaseExpandableListAdapter
        implements View.OnClickListener{

    public String[] groups;
    public String[][] persons;
    public Context context;
    public View.OnClickListener ivGotoChildeListener;

    public MyExpandableListViewAdapter(String[] groups, String[][] persons, Context context,
                                       View.OnClickListener ivGotoChildeListener) {
        this.groups = groups;
        this.persons = persons;
        this.context = context;
        this.ivGotoChildeListener = ivGotoChildeListener;
    }

    @Override
    public int getGroupCount() { // 分组的数量
        return groups.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) { // 某个分组的子项的数量
        return persons[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) { // 指定位置的分组
        return groups[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) { // 某个分组的某个子项
        return persons[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) { // 分组的id
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) { // 子项目的id
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {
        GroupHolder groupHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.expand_group,null);
            groupHolder = new GroupHolder();
            groupHolder.tvGroupName = (TextView) convertView.findViewById(R.id.tv_expand_group);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }

        String groupName = groups[groupPosition];
        groupHolder.tvGroupName.setText(groupName);
        // 设置 tag
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("groupPosition",groupPosition);
        hashMap.put("isExpand",isExpanded);
        groupHolder.tvGroupName.setTag(hashMap);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        PersonHolder personHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.expand_item,null);
            personHolder = new PersonHolder();
            personHolder.tvPersonName = (TextView) convertView.findViewById(R.id.tv_expand_item);
            convertView.setTag(personHolder);
        } else {
            personHolder = (PersonHolder) convertView.getTag();
        }
        personHolder.tvPersonName.setText(persons[groupPosition][childPosition]);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) { // 指定位置上的元素是否可以被选中
        return true;
    }

    @Override
    public void onClick(View v) {

    }

    class GroupHolder {
         TextView tvGroupName;
    }

    class PersonHolder {
        public TextView tvPersonName;
    }
}
