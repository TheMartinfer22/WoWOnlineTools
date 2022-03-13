package dev.nanosync.wowonlinetools.repository;

import dev.nanosync.wowonlinetools.entity.ItemEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(value = "itemapi", url = "https://us.api.blizzard.com/data/wow")
public interface ItemRepository {
    @RequestMapping(value = "/item/{id}?namespace=static-us&locale=pt_BR&access_token={token}", method = GET, produces = "application/json")
    ItemEntity getItemByID(@PathVariable("id") Long id, @PathVariable("token") String token);
}
