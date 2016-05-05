package gyndroids.com.savingwatertips.ui.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gyndroids.com.savingwatertips.R;
import gyndroids.com.savingwatertips.interfaces.InterfaceGridItemSelected;
import gyndroids.com.savingwatertips.utils.Configs;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMain extends Fragment {

    private View mView;
    private InterfaceGridItemSelected mInterfaceGridItemSelected;

    public FragmentMain() {
        // Required empty public constructor
    }

    // factory pattern to create a new instance of this fragment.
    public static FragmentMain newInstance() {
        return new FragmentMain();
    }

    //
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;

        try {
            mInterfaceGridItemSelected = (InterfaceGridItemSelected) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement InterfaceDrawer");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_main, container, false);

        GridView gridView = (GridView) mView.findViewById(R.id.main_gridview);
        gridView.setAdapter(new MyAdapter(getContext()));

        gridView.setOnItemClickListener((parent, v, position, id) ->
                mInterfaceGridItemSelected.onGridItemSelected(position));


        return mView;
    }

    private class MyAdapter extends BaseAdapter {
        private List<Item> items = new ArrayList<>();
        private LayoutInflater inflater;

        public MyAdapter(Context context) {
            inflater = LayoutInflater.from(context);

            for (int i = 0; i < 15; i++) {
                items.add(new Item(getString(
                        Configs.getTitleResourceIdByPosition(i)),
                        Configs.getImageThumbResourceByPosition(i)));
            }
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return items.get(i).drawableId;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = view;
            ImageView picture;
            TextView name;

            if (v == null) {
                v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
                v.setTag(R.id.gridview_item_picture, v.findViewById(R.id.gridview_item_picture));
                v.setTag(R.id.gridview_item_title, v.findViewById(R.id.gridview_item_title));
            }

            picture = (ImageView) v.getTag(R.id.gridview_item_picture);
            name = (TextView) v.getTag(R.id.gridview_item_title);

            Item item = (Item) getItem(i);

            picture.setImageResource(item.drawableId);
            name.setText(item.name);

            return v;
        }

        private class Item {
            final String name;
            final int drawableId;

            Item(String name, int drawableId) {
                this.name = name;
                this.drawableId = drawableId;
            }
        }
    }

}
