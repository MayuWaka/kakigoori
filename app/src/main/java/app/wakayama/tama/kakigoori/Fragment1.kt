package app.wakayama.tama.kakigoori

import android.content.Intent
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import kotlinx.android.synthetic.main.fragment_1.*
import java.util.*


class Fragment1 : Fragment() {
//    private lateinit var placesClient: PlacesClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val layout = inflater.inflate(R.layout.fragment_1, container, false)

//        val apiKey = getString(R.string.google_maps_key)
//        if (apiKey.isEmpty()) {
//            Toast.makeText(context!!, "NO API Key", Toast.LENGTH_LONG).show()
//        }
//        if (!Places.isInitialized()) {
//            Places.initialize(context!!, apiKey)
//        }
//        placesClient = Places.createClient(context!!)
//
//        // Initialize the AutocompleteSupportFragment.
//        val autocompleteFragment =
//         supportFragmentManager?.findFragmentById(R.id.autocomplete_support_fragment) as AutocompleteSupportFragment?
//
//        val autocompleteFragment =
//          fragmentManager?.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment?
//
//        // Specify the types of place data to return.
//        autocompleteFragment?.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME))
//
// Set up a PlaceSelectionListener to handle the response.
//        autocompleteFragment?.setOnPlaceSelectedListener(object : PlaceSelectionListener {
//            override fun onPlaceSelected(place: Place) {
//                // TODO: Get info about the selected place.
//                Log.i("tag", "Place: " + place.name + ", " + place.id)
//            }
//
//          override  fun onError(status: Status) {
//                // TODO: Handle the error.
//                Log.i("tag", "An error occurred: $status")
//            }
//        })

        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var angleInDegrees: Float = 25.0F
        koriImageView.setRotation(angleInDegrees);
        angleInDegrees = -25.0F
        koriImageView2.setRotation(angleInDegrees);

        searchButton.setOnClickListener{
            //Google Map表示
    //        val mapUrl:String = "geo:0,0?q=" + lat + "," + lng  + "(" + label + ")";
            val searchArea: String = searchAreaTextView.text.toString()
            val mapUrl:String = "geo:0,0?q=" + searchArea + " かき氷"
            val sendIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl))
    //        val sendIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=東京駅"))
            startActivity(sendIntent)
        }
    }
}