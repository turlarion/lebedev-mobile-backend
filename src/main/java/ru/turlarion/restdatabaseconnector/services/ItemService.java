package ru.turlarion.restdatabaseconnector.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.turlarion.restdatabaseconnector.api.pojos.ModifyItemInfoPojo;
import ru.turlarion.restdatabaseconnector.api.pojos.NewItemPojo;
import ru.turlarion.restdatabaseconnector.db.entities.Item;
import ru.turlarion.restdatabaseconnector.db.entities.User;
import ru.turlarion.restdatabaseconnector.db.repositories.ItemRepository;
import ru.turlarion.restdatabaseconnector.db.repositories.UserRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public ItemService(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public boolean saveNewItem(NewItemPojo newItemPojo) {
        Optional<User> user = userRepository.findById(newItemPojo.getUserId());
        if (user.isEmpty()) {
            return false;
        }
        Item newItem = new Item(user.get(), newItemPojo.getName(), newItemPojo.getDescription(), newItemPojo.getPrice(),
            newItemPojo.getPicName(), new Date());
        itemRepository.save(newItem);
        return true;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Integer id) {
        return itemRepository.findById(id).isPresent() ? itemRepository.findById(id).get() : null;
    }

    public void removeById(Integer id) {
        itemRepository.deleteById(id);
    }

    public boolean modifyById(Integer id, ModifyItemInfoPojo mod) {
        if (mod.getName() == null && mod.getPicName() == null
            && mod.getDescription() == null && mod.getPrice() == null) {
            return false;
        }

        if ((mod.getName() != null && mod.getName().isBlank())
            || (mod.getDescription() != null && mod.getDescription().isBlank())
            || (mod.getPicName() != null && mod.getPicName().isBlank())
            || (mod.getPrice() != null && mod.getPrice().isBlank())) {
            return false;
        }

        Item item = itemRepository.getReferenceById(id);

        if (mod.getName() != null) {
            item.setName(mod.getName());
        }
        if (mod.getDescription() != null) {
            item.setDescription(mod.getDescription());
        }
        if (mod.getPrice() != null) {
            item.setPrice(mod.getPrice());
        }
        if (mod.getPicName() != null) {
            item.setPicName(mod.getPicName());
        }

        itemRepository.save(item);

        return true;
    }

}
