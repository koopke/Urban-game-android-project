package koopke.master;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class ItemizedOverlay extends com.google.android.maps.ItemizedOverlay {
	
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	Context mContext;
	int tankflag[];
	MapView mapView;


	public ItemizedOverlay(Drawable defaultMarker, Context context,int[] flag) {
		super(boundCenter(defaultMarker));
		mContext = context;
		tankflag=flag;
		

	}
	
	@Override
	protected boolean onTap(int index) {
	  OverlayItem item = mOverlays.get(index);
	  AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
	  dialog.setTitle(item.getTitle());
	  dialog.setMessage(item.getSnippet());
	  dialog.show();
	  return true;
	}
	
	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}
	
	@Override
	protected OverlayItem createItem(int i) {
	  return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();

	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event , MapView mapView){
		if (tankflag[0]==1){
			System.out.println("THNKS GOD!!!!");

		}
		

		return false;
	}

}
