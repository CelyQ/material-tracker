package materials.tracker.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import materials.tracker.domain.Material;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class MaterialService {
    public static final String MATERIALS_COLLECTION="materials";
    public static final String SETTINGS_COLLECTION="settings";


    public List<Material> getMaterials() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(MATERIALS_COLLECTION).whereGreaterThanOrEqualTo("id", 0).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        if (documents.isEmpty()) return null;
        return documents.stream().map(d -> d.toObject(Material.class)).collect(Collectors.toList());
    }

    public String saveMaterial(Material material) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        Map<String, Object> materialProps = dbFirestore.collection(SETTINGS_COLLECTION)
                .document("materials_props")
                .get()
                .get()
                .getData();

        if (materialProps == null) {
            Map<String, Object> updatedMaterialProps = new HashMap<>();
            updatedMaterialProps.put("auto_incremented_value", (long) 0);
            dbFirestore.collection(SETTINGS_COLLECTION).document("materials_props").set(updatedMaterialProps, SetOptions.merge());
            materialProps = updatedMaterialProps;
        }

        long autoIncrementedValue = (long) materialProps.get("auto_incremented_value") + 1;

        Map<String, Object> docData = new HashMap<>();
        docData.put("id", autoIncrementedValue);
        docData.put("name", material.getName());
        docData.put("imageURL", material.getImageURL());
        docData.put("pagesProgress", material.getPagesProgress());
        docData.put("pagesTotal", material.getPagesTotal());

        materialProps.put("auto_incremented_value", autoIncrementedValue);

        ApiFuture<WriteResult> future = dbFirestore.collection(MATERIALS_COLLECTION).document(autoIncrementedValue + ". material").set(docData, SetOptions.merge());

        dbFirestore.collection(SETTINGS_COLLECTION).document("materials_props")
                .set(materialProps);

        return "Update time: " + future.get().getUpdateTime();
    }
}
