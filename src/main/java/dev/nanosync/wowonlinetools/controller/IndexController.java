package dev.nanosync.wowonlinetools.controller;

import dev.nanosync.wowonlinetools.entity.ItemEntity;
import dev.nanosync.wowonlinetools.repository.ItemRepository;
import feign.FeignException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

@Controller
public class IndexController {

    private final ItemRepository itemRepository;
    private final String token;

    @SneakyThrows
    public IndexController(ItemRepository itemRepository) throws FileNotFoundException {
        this.itemRepository = itemRepository;
        Properties properties = new Properties();
        FileReader reader = new FileReader("auth.properties");
        properties.load(reader);
        this.token = properties.getProperty("TOKEN");
    }

    @GetMapping("/{id}")
    public String itemTest(@PathVariable("id") Long id, ModelMap model){
        try {
            ItemEntity item = itemRepository.getItemByID(id, token);
            model.addAttribute("item_id", item.getId());
            model.addAttribute("item_name", item.getName());
            model.addAttribute("item_level", item.getLevel());
            model.addAttribute("status_search", "Encontrado");
        } catch (FeignException.NotFound exception$NotFound){
            model.addAttribute("status_search", "Item não encontrado");
        }
        return "index";
    }
}

