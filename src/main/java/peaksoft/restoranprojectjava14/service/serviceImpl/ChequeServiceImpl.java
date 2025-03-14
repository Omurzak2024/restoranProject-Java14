package peaksoft.restoranprojectjava14.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.restoranprojectjava14.dto.request.ChequeRequest;
import peaksoft.restoranprojectjava14.dto.response.AverageSumResponse;
import peaksoft.restoranprojectjava14.dto.response.ChequeTotalWaiterResponse;
import peaksoft.restoranprojectjava14.dto.response.SimpleResponse;
import peaksoft.restoranprojectjava14.dto.response.pagination.PaginationResponseCheque;
import peaksoft.restoranprojectjava14.entity.Cheque;
import peaksoft.restoranprojectjava14.entity.MenuItem;
import peaksoft.restoranprojectjava14.entity.User;
import peaksoft.restoranprojectjava14.enums.Role;
import peaksoft.restoranprojectjava14.exceptions.NotFoundException;
import peaksoft.restoranprojectjava14.repository.ChequeRepository;
import peaksoft.restoranprojectjava14.repository.MenuItemRepository;
import peaksoft.restoranprojectjava14.repository.UserRepository;
import peaksoft.restoranprojectjava14.service.ChequeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChequeServiceImpl implements ChequeService {
    private final ChequeRepository chequeRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository MenuItemRepository;
    private final MenuItemRepository menuItemRepository;

    private User getAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.getUserByEmail(name)
                .orElseThrow(
                        ()-> new NotFoundException("User with email " + name + " us bit found!"));
        return user;
    }


    @Override
    public PaginationResponseCheque getAllCheques() {
        return null;
    }

    @Override
    public SimpleResponse saveCheque(Long userId, ChequeRequest chequeRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        ()-> new NotFoundException(String.format("User with id %s not found!", userId)));
        Cheque cheque = new Cheque();
        List<Cheque> cheques = new ArrayList<>();
        cheques.add(cheque);
        List<MenuItem> items = new ArrayList<>();
        for (Long l : chequeRequest.getMenuItemNames()){
            MenuItem menuItem = menuItemRepository.findById(l)
                    .orElseThrow(() -> new NotFoundException(String.format("MenuItem with id %s not found!", l)));
            menuItem.setCheques(cheques);
            items.add(menuItem);
        }
        int totalPrice = 0;
        for (MenuItem menuItem : items){
            totalPrice += menuItem.getPrice();
        }

        cheque.setUser(user);
        cheque.setCreatedAt(LocalDate.now());
        cheque.setMenuItems(items);
        cheque.setPriceAverage(totalPrice);
        chequeRepository.save(cheque);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully saved cheque!")
                .build();
    }

    @Override
    public SimpleResponse updateCheque(Long id, ChequeRequest chequeRequest) {
        Cheque cheque = chequeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Cheque with id: %s is not present!", id)));
        List<MenuItem> items = new ArrayList<>();
        for (Long l : chequeRequest.getMenuItemNames()){
            Optional<MenuItem> menuItem = menuItemRepository.findById(l);
            MenuItem menuItem1 = new MenuItem();
            menuItem1.setId(menuItem.get().getId());
            menuItem1.setName(menuItem.get().getName());
            menuItem1.setImage(menuItem.get().getImage());
            menuItem1.setRestaurant(menuItem.get().getRestaurant());
            menuItem1.setVegetarian(menuItem.get().isVegetarian());
            menuItem1.setPrice(menuItem.get().getPrice());
            items.add(menuItem1);
        }
        int totalPrice = 0;
        for (MenuItem menuItem : items){
            totalPrice += menuItem.getPrice();
        }
        cheque.setPriceAverage(totalPrice);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully updated cheque!")
                .build();
    }

    @Override
    public ChequeTotalWaiterResponse chequeTotalByWaiter(Long waiterId, LocalDate date) {
        int counterCheck = 0;
        int totalPrice = 0;
        User user = userRepository.findById(waiterId).orElseThrow();
        if (user.getRole().equals(Role.WAITER)){
            for (Cheque cheque : user.getCheques()){
                if (cheque.getCreatedAt().isEqual(date)){
                    int service = cheque.getGrandTotal()*user.getRestaurant().getService()/100;
                    totalPrice += service + cheque.getGrandTotal();
                    counterCheck++;
                }
            }
        }
        return ChequeTotalWaiterResponse
                .builder()
                .waiterName(user.getFirstName() + " " + user.getLastName())
                .counterCheck(counterCheck)
                .date(date)
                .totalPrice(totalPrice)
                .build();
    }

    @Override
    public SimpleResponse deleteCheque(Long id) {
        if (chequeRepository.existsById(id)) {
            chequeRepository.deleteById(id);

            return SimpleResponse
                    .builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully deleted cheque!")
                    .build();
        }else throw new NotFoundException(String.format("Cheque with id %s not found!", id));
    }

    @Override
    public AverageSumResponse getAverageSum(LocalDate date) {
        int i = 0;
        List<Cheque> cheques = new ArrayList<>();
        List<User> all = userRepository.findAll();
        for (int j = 0; j < all.size(); j++){
            cheques.addAll(all.get(j).getCheques());
        }
        for (int k = 0; k < cheques.size(); k++){
            if (cheques.get(k).getCreatedAt().equals(date)){
                i += cheques.get(k).getPriceAverage();
            }
        }
        return AverageSumResponse
                .builder()
                .sum(i)
                .build();
    }

    @Override
    public AverageSumResponse getAverageSumOfWaiter(Long waiterId, LocalDate dateTime) {
        User user = userRepository.findById(waiterId).orElseThrow(
                ()-> new NotFoundException(String.format("Waiter with id %s not found!", waiterId)));
        int i = chequeRepository.averageSumOfWaiter(user.getId(), dateTime);
        return AverageSumResponse
                .builder()
                .sum(i)
                .build();
    }
}
