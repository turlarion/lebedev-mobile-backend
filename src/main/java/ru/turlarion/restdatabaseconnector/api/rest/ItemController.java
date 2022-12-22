package ru.turlarion.restdatabaseconnector.api.rest;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.turlarion.restdatabaseconnector.api.dto.ItemDTO;
import ru.turlarion.restdatabaseconnector.api.pojos.ModifyItemInfoPojo;
import ru.turlarion.restdatabaseconnector.api.pojos.NewItemPojo;
import ru.turlarion.restdatabaseconnector.db.entities.Item;
import ru.turlarion.restdatabaseconnector.services.ItemService;

@RestController
@RequestMapping(path = "/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addNewItem(@RequestBody NewItemPojo newItemPojo) {
        if (itemService.saveNewItem(newItemPojo)) {
            return ResponseEntity.ok("");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check user id and request body");
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems().stream()
            .map(item ->
                ItemDTO.builder()
                .item_id(item.getItem_id())
                .price(item.getPrice())
                .picName(item.getPicName())
                .name(item.getName())
                .description(item.getDescription())
                .date(item.getDate())
                .user_id(item.getUser().getUser_id())
                .build())
            .toList();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ItemDTO getItemById(@PathVariable Integer id) {
        Item item = itemService.getItemById(id);
        return item == null ?
            null :
            ItemDTO.builder()
                .item_id(item.getItem_id())
                .price(item.getPrice())
                .picName(item.getPicName())
                .name(item.getName())
                .description(item.getDescription())
                .date(item.getDate())
                .user_id(item.getUser().getUser_id())
                .build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public void removeItemById(@PathVariable Integer id) {
        itemService.removeById(id);
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/{id}")
    public ResponseEntity<String> updateItemInfo(@PathVariable Integer id, @RequestBody ModifyItemInfoPojo mod) {
        if (itemService.modifyById(id, mod)) {
            return ResponseEntity.ok("");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check request body!");
    }
}
