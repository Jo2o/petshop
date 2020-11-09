package sk.gw.jo2o.petshop.shopping.services;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.entity.OrderedItem;
import sk.gw.jo2o.petshop.repo.OrderedItemRepository;

@Service
@RequiredArgsConstructor
public class OrderedItemService {

    private final OrderedItemRepository orderedItemRepository;

    public void save(OrderedItem orderedItem) {
        orderedItemRepository.save(orderedItem);
    }

}
