package com.autotagging.service;

/**
 * Created by Kaiser Ahmed on 3/14/2017.
 */

import java.util.ArrayList;
import java.util.List;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.input.image.ClarifaiImage;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;


public class ImageTagging {
    // Provide your Client ID
    private final static String CLIENT_ID = "0AbAUCd_zQvnY_dZo6FaKMhfNwWtjunzDzW6p3Uf";

    // Provider Your Client Secret Key
    private final static String CLIENT_SECRET_KEY = "EUj1JnOaF9-AwFXM0R3bl2iijquzgffaNv2Nrzpa";

    public static List<String> findTags(String imageUrl) {

        // Defining List Object
        List<String> resultList = new ArrayList<String>();

        if (imageUrl != null && !imageUrl.isEmpty()) {

            final ClarifaiClient client = new ClarifaiBuilder(CLIENT_ID, CLIENT_SECRET_KEY).buildSync();

            final List<ClarifaiOutput<Concept>> predictionResults =
                    client.getDefaultModels().generalModel() // You can also do client.getModelByID("id") to get custom models
                            .predict()
                            .withInputs(
                                    ClarifaiInput.forImage(ClarifaiImage.of(imageUrl))
                            )
                            .executeSync()
                            .get();

            if (predictionResults != null && predictionResults.size() > 0) {

                // Prediction List Iteration
                for (int i = 0; i < predictionResults.size(); i++) {

                    ClarifaiOutput<Concept> clarifaiOutput = predictionResults.get(i);

                    List<Concept> concepts = clarifaiOutput.data();

                    if (concepts != null && concepts.size() > 0) {
                        for (int j = 0; j < concepts.size(); j++) {

                            resultList.add(concepts.get(j).name());
                        }
                    }
                }
            }

        }
        return resultList;
    }

}
