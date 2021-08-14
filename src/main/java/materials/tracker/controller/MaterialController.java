package materials.tracker.controller;

import materials.tracker.domain.Material;
import materials.tracker.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class MaterialController {
    @Autowired
    MaterialService materialService;

    @GetMapping("/api/getMaterials")
    public List<Material> getMaterial() throws InterruptedException, ExecutionException {
        return materialService.getMaterials();
    }

    @PostMapping("/api/saveMaterial")
    public String saveMaterial(@RequestBody Material material) throws InterruptedException, ExecutionException {
        return materialService.saveMaterial(material);
    }
}
