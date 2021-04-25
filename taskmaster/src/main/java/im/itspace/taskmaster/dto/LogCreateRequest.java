package im.itspace.taskmaster.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogCreateRequest {

    private Date date;

    private int TaskId;

    private double hours;

}
