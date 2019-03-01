package com.voltaps.voltapowersystems;

import android.widget.BaseAdapter;
import android.content.Context;
import java.util.ArrayList;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;

/**
 * Description:
 * Class to display 2 item list.
 */
public class TwoItemListAdapter extends BaseAdapter {

    private Context context;
    private int layoutResourceID;
    private ArrayList<BatteryAttribute> attributes;

    TwoItemListAdapter(Context context, int resource, ArrayList<BatteryAttribute> attributes) {
        this.context = context;
        this.attributes = attributes;
        layoutResourceID = resource;
    }

    /**
     * Description:
     * Returns number of list items.
     * @return - returns number of listed items.
     */
    @Override
    public int getCount() {
        return attributes.size();
    }

    /**
     * Description:
     * Get an item in the list.
     * @param position
     * position in list.
     * @return - Device object in list.
     */
    @Override
    public Object getItem(int position) {
        return attributes.get(position);
    }

    /**
     * Description:
     * Get item ID. Not implemented, do NOT use.
     * @param position - Position in list.
     * @return - 0.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Description:
     * View to define how to display the data.
     * @param position - Position in list.
     * @param convertedView - View the list will be displayed in.
     * @param parent - Parent ViewGroup.
     * @return - View
     */
    @Override
    public View getView(int position, View convertedView, ViewGroup parent) {
        // Make sure we have a view to use
        View itemView = convertedView;
        if (itemView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(layoutResourceID, parent, false);
        }

        // Find the device
        BatteryAttribute currentDevice = attributes.get(position);

        // Fill the View (show the data from the BatteryAttribute class)
        TextView dAttributeText = itemView.findViewById(R.id.attribute);
        dAttributeText.setText(currentDevice.getAttribute());

        TextView dValueText = itemView.findViewById(R.id.value);
        dValueText.setText(currentDevice.getValue());

        TextView dUnitText = itemView.findViewById(R.id.unit);
        dUnitText.setText(currentDevice.getUnit());

        // Return the View
        return itemView;
    }
}
