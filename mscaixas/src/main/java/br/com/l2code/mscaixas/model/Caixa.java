package br.com.l2code.mscaixas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "caixas", uniqueConstraints = {
        @UniqueConstraint(
                name = "tamanhos_uk",
                columnNames = {"altura", "largura", "comprimento"}
        )
})
@Getter
@Setter
@ToString
public class Caixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal altura;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal largura;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal comprimento;

    public BigDecimal getVolume() {
        return altura.multiply(largura).multiply(comprimento);
    }

    public Caixa() {

    }

    public Caixa(Long id, BigDecimal altura, BigDecimal largura, BigDecimal comprimento) {
        this(altura, largura, comprimento);
        this.id = id;
    }

    public Caixa(BigDecimal altura, BigDecimal largura, BigDecimal comprimento) {
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Caixa caixa = (Caixa) o;
        return getId() != null && Objects.equals(getId(), caixa.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
