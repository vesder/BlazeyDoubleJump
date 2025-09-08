package me.vesder.blazeydoublejump.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private boolean jumpAllowed;
    private long lastJumpTime;
}
