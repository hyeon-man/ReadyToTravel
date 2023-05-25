package kr.ac.kopo.ReadyToTravel.root;

import kr.ac.kopo.ReadyToTravel.entity.PlaceEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService{
    private final PlaceRepository placeRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public List<PlaceEntity> plaecList() {
        return placeRepository.findAll();
    }
}
