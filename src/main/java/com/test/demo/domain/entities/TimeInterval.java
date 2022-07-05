package com.test.demo.domain.entities;

import lombok.*;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class TimeInterval {

    @NonNull
    private Long id;

    @NonNull
    private String start;

    @NonNull
    private String end;

    private String intervalDuration;

    private String breakDuration;

}
