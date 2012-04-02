package koopke.master;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MapView.LayoutParams;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;



public class UrbanGameActivity extends MapActivity {
    /** Called when the activity is first created. */
	

	int tankButtonFlag[]={0};
	GeoPoint p=new GeoPoint(19240000,-99120000);
	MapView mapView;
	Drawable drawable;
	ItemizedOverlay itemizedoverlayTank;
	
	
	//---add tank as drawable---
	//Drawable drawable = getResources().getDrawable(R.drawable.tankicon02);

	//ItemizedOverlay itemizedoverlayTank = new ItemizedOverlay(tankDrawable, this,tankButtonFlag);
	

	
	

	
	public void createTank(MapView mapView){
		
		List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.tankicon02);
        ItemizedOverlay itemizedoverlayTank = new ItemizedOverlay(drawable, this,tankButtonFlag);
        OverlayItem overlayitem = new OverlayItem(p, "Hola, Mundo!", "I'm in Mexico City!");
        itemizedoverlayTank.addOverlay(overlayitem);
        mapOverlays.add(itemizedoverlayTank);
        mapView.invalidate();
        System.out.println(tankButtonFlag[0]);
		
	}
	
	
	
	/*class mapOverlay extends com.google.android.maps.Overlay{

		@Override
		public boolean onTouchEvent(MotionEvent event , MapView mapView){
			//---when user lifts his finger---
			if (event.getAction()==1 && tankButtonFlag==1){
				
				GeoPoint p = mapView.getProjection().fromPixels((int)event.getX(), (int)event.getY());
				Toast.makeText(getBaseContext(),p.getLatitudeE6() / 1E6+","+p.getLongitudeE6()/1E6, Toast.LENGTH_SHORT).show();
				AddTank(mapView,p);
				tankButtonFlag=0;
			}
			return false;
		}
	
	}*/
	
	/*public void AddTank(MapView mapView,GeoPoint p,int tankButtonFlag){
		//List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.tankicon02);
		ItemizedOverlay itemizedoverlay = new ItemizedOverlay(drawable, this);
		OverlayItem overlayitem = new OverlayItem(p, "Hola, Mundo!", "I'm in Mexico City!");
		itemizedoverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedoverlay);
		mapView.invalidate();
	}*/
	

	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        mapView = (MapView)findViewById(R.id.mapView);
        MapController mapController = mapView.getController(); //now I got control over the mapView
        String tanksCoordinates[];
        GeoPoint tankGeoPoint;
        
        //---Add Zoom keys---
        LinearLayout zoomLayout = (LinearLayout)findViewById(R.id.zoom);
        View zoomView = mapView.getZoomControls();
        zoomLayout.addView(zoomView, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        mapView.displayZoomControls(true);
        

        
        //---Button Create Tank---
        Button createTankButton =(Button)findViewById(R.id.createTankButton);
        createTankButton.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v) {
            Toast.makeText(getBaseContext(), "This is a Test for Toast",Toast.LENGTH_LONG ).show();
            System.out.println("tank button flag ="+tankButtonFlag[0]);
            tankButtonFlag[0]=1;
            System.out.println("tank button flag ="+tankButtonFlag[0]);
            System.out.println(mapView);
            p = mapView.getMapCenter();
            System.out.println("this is p:" +p);
            
            List<Overlay> mapOverlays = mapView.getOverlays();
            OverlayItem overlayitem = new OverlayItem(p, "Hola, Mundo!", "I'm in Mexico City!");
            itemizedoverlayTank.addOverlay(overlayitem);
            mapOverlays.add(itemizedoverlayTank);
            mapView.invalidate();
            

            }});

        

        
        List<Overlay> mapOverlays = mapView.getOverlays();
        drawable = this.getResources().getDrawable(R.drawable.tankicon02);
        itemizedoverlayTank = new ItemizedOverlay(drawable, this,tankButtonFlag);
        OverlayItem overlayitem = new OverlayItem(p, "Hola, Mundo!", "I'm in Mexico City!");
        itemizedoverlayTank.addOverlay(overlayitem);
        mapOverlays.add(itemizedoverlayTank);
        mapView.invalidate();
        System.out.println(tankButtonFlag[0]);
        
        
        
 
    }
    
    @Override
    protected boolean isRouteDisplayed() {
    	return false;
    }
}