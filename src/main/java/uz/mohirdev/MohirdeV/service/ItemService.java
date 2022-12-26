package uz.mohirdev.MohirdeV.service;

import org.springframework.stereotype.Service;
import uz.mohirdev.MohirdeV.Entity.Item;
import uz.mohirdev.MohirdeV.repository.ItemRepository;

import java.util.Optional;


@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item save(Item item){
        return itemRepository.save(item);
    }

    public Item get(Long id){
        Optional<Item> result = itemRepository.findById(id);
        if (result.isPresent()) return result.get();
        else return null;
    }

    public void delete(Long id){
        itemRepository.deleteById(id);
    }
}
