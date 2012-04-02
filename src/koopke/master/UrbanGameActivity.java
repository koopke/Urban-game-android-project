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
	
	//---tell the overlay I pressed the button ---
	int tankButtonFlag[]={0,0};
	
	//---random place just to put one overlay---
	GeoPoint p=new GeoPoint(19240000,-99120000);
	MapView mapView;
	Drawable drawableTank;
	ItemizedOverlay itemizedoverlayTank;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        mapView = (MapView)findViewById(R.id.mapView);
        MapController mapController = mapView.getController(); //now I got control over the mapView
        String coordinates[] = {"31.2654","34.7995"};
        double lat = Double.parseDouble(coordinates[0]);
        double lon = Double.parseDouble(coordinates[1]);
        GeoPoint startView = new GeoPoint((int)(lat * 1E6), (int)(lon * 1E6));
        mapController.animateTo(startView); // put the map on the GeoPoint
        mapController.setZoom(17); // set the map zoom

        
        //---Add Zoom keys---
        LinearLayout zoomLayout = (LinearLayout)findViewById(R.id.zoom);
        View zoomView = mapView.getZoomControls();
        zoomLayout.addView(zoomView, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        mapView.displayZoomControls(true);
        

        
        //---Button Create Tank---
        Button createTankButton =(Button)findViewById(R.id.createTankButton);
        createTankButton.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v) {
        		

            tankButtonFlag[0]=1; //The button has been pressed
            tankButtonFlag[1]++;
            p = mapView.getMapCenter();
            
            List<Overlay> mapOverlays = mapView.getOverlays();
            OverlayItem overlayitem = new OverlayItem(p, "Tank #"+tankButtonFlag[1],String.valueOf(p));
            itemizedoverlayTank.addOverlay(overlayitem);
            mapOverlays.add(itemizedoverlayTank);
            mapView.invalidate();
            }});

      
        ///insert fist tank just to get some overlay
        //List<Overlay> mapOverlays = mapView.getOverlays();
        drawableTank = this.getResources().getDrawable(R.drawable.tankicon02);
        itemizedoverlayTank = new ItemizedOverlay(drawableTank, this,tankButtonFlag);
        //OverlayItem overlayitem = new OverlayItem(p, "Hola, Mundo!", "I'm in Mexico City!");
        //itemizedoverlayTank.addOverlay(overlayitem);
        //mapOverlays.add(itemizedoverlayTank);
        mapView.invalidate();

    }
    
    @Override
    protected boolean isRouteDisplayed() {
    	return false;
    }
}