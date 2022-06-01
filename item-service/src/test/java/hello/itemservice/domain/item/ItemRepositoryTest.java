package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clear();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);
        //when
        Item savedItem = itemRepository.save(item);
        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);

    }

    @Test
    void findAll() {
        //given
        Item item = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 5);
        Item item3 = new Item("itemC", 30000, 15);

        itemRepository.save(item);
        itemRepository.save(item2);
        itemRepository.save(item3);
        //when
        List<Item> result = itemRepository.finaAll();

        //then
        assertThat(result.size()).isEqualTo(3);
        assertThat(result).contains(item, item2, item3);
    }

    @Test
    void update() {
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item savedItem = itemRepository.save(item1);
        Long itemId = savedItem.getId();

        //when
        Item updateParam = new Item("item2", 20000, 5);
        itemRepository.update(itemId, updateParam);
        //then
        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}