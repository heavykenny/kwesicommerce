package com.example.kwesicommerce.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.CategoryModel;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<CategoryModel> {
    private LayoutInflater inflater;

    public CustomSpinnerAdapter(Context context, List<CategoryModel> items) {
        super(context, R.layout.layout_spinner_item, items);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.layout_spinner_item, parent, false);
        }

        TextView textView = view.findViewById(R.id.txtViewCategoryName);
        textView.setText(getItem(position).getName());

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
