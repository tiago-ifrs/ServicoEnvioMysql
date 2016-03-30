package entidades;

import entidades.Leitura;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-14T02:35:33")
@StaticMetamodel(Dado.class)
public class Dado_ { 

    public static volatile SingularAttribute<Dado, Float> valor;
    public static volatile SingularAttribute<Dado, Date> tempo;
    public static volatile SingularAttribute<Dado, Integer> sensor;
    public static volatile SingularAttribute<Dado, Integer> idDado;
    public static volatile SingularAttribute<Dado, Leitura> idLeitura;
    public static volatile SingularAttribute<Dado, String> hash;

}