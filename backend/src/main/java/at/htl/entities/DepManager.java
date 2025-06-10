package at.htl.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Manager")
public class DepManager extends Employee {
}


