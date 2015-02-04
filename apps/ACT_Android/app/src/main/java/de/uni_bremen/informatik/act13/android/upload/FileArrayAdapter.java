/**
 * 
 */
package de.uni_bremen.informatik.act13.android.upload;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import de.uni_bremen.informatik.act13.android.R;

/**
 * {@link ArrayAdapter} for {@link File}s. Also displays the size and a checkbox
 * for every file.
 * 
 * @author Roman Gischkowski
 * 
 */
public class FileArrayAdapter extends ArrayAdapter<List<File>> {

	private Context context;
	private Set<List<File>> checkedEntries = new HashSet<List<File>>();

	/**
	 * Constructs a new object.
	 * 
	 * @param context
	 * @param resource
	 */
	public FileArrayAdapter(Context context, int resource) {
		super(context, resource);
		this.context = context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ArrayAdapter#clear()
	 */
	@Override
	public void clear() {
		checkedEntries.clear();
		super.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ArrayAdapter#remove(java.lang.Object)
	 */
	@Override
	public void remove(List<File> object) {
		checkedEntries.remove(object);
		super.remove(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.listview_item_upload, parent, false);

		final List<File> item = getItem(position);

		CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox_selected);
		checkbox.setText(item.get(0).getName());
		checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					checkedEntries.add(item);
				} else {
					checkedEntries.remove(item);
				}
			}
		});
		checkbox.setChecked(checkedEntries.contains(item));

		TextView textview = (TextView) view.findViewById(R.id.textview_size);
		long length = 0;
		for (File file : item) {
			length += file.length();
		}
		textview.setText(bytes(length, true));

		return view;
	}

	/**
	 * http://stackoverflow.com/questions/3758606
	 * 
	 * @param bytes
	 * @param si
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	private String bytes(long bytes, boolean si) {
		int unit = si ? 1000 : 1024;
		if (bytes < unit)
			return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

	/**
	 * Toggles the state of every checkbox in the list.
	 */
	public void toggleCheckAll() {
		if (checkedEntries.isEmpty()) {
			for (int i = 0; i < getCount(); i++) {
				checkedEntries.add(getItem(i));
			}
		} else {
			checkedEntries.clear();
		}
	}

	/**
	 * Returns a copy of the selected items.
	 * 
	 * @return
	 */
	public List<List<File>> getCheckedEntries() {
		List<List<File>> entries = new ArrayList<List<File>>(checkedEntries.size());
		for (List<File> entry : checkedEntries) {
			entries.add(entry);
		}
		return entries;
	}

}
