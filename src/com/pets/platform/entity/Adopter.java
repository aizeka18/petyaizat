@Entity
@Table(name = "adopter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Adopter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String contactInfo;
}
