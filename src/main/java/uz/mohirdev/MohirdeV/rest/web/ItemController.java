package uz.mohirdev.MohirdeV.rest.web;


import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mohirdev.MohirdeV.Entity.Item;
import uz.mohirdev.MohirdeV.service.ItemService;

@RestController
@RequestMapping("/api")
public class ItemController {


    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/items")
    public ResponseEntity save(@RequestBody Item item){
        Item result = itemService.save(item);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/items")
    public ResponseEntity update(@RequestBody Item item){
        if (item.getId()==null) return ResponseEntity.badRequest().build();
        Item result = itemService.save(item);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/items")
    public ResponseEntity get(@RequestParam Long id){
        Item result = itemService.get(id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/items")
    public ResponseEntity delete(@RequestParam Long id){
        itemService.delete(id);
        return ResponseEntity.ok("Item is deleted");
    }
}
