package com.stayeasy.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
//import com.stayeasy.dto.RegisterHostelLoctionDTO;

@Getter
@Setter
public class RegisterHostelDTO {
    private String hostelName;
    private String address;
    private Long ownerId;
    private RegisterHostelLocationDTO location;
    private List<RoomDTO> rooms;
}
