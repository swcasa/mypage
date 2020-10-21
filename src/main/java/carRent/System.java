package carRent;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="System_table")
public class System {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long payId;
        private Long carId;
        private String status;
        private Integer qty;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Long getPayId() {
            return payId;
        }

        public void setPayId(Long payId) {
            this.payId = payId;
        }
        public Long getCarId() {
            return carId;
        }

        public void setCarId(Long carId) {
            this.carId = carId;
        }
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        public Integer getQty() {
            return qty;
        }

        public void setQty(Integer qty) {
            this.qty = qty;
        }

}
