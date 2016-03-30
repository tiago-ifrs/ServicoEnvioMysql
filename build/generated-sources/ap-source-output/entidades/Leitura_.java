package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-14T02:35:33")
@StaticMetamodel(Leitura.class)
public class Leitura_ { 

    public static volatile SingularAttribute<Leitura, Float> intervaloEntreAmostras;
    public static volatile SingularAttribute<Leitura, String> ip;
    public static volatile SingularAttribute<Leitura, Date> dataLeitura;
    public static volatile SingularAttribute<Leitura, Integer> idLeitura;
    public static volatile SingularAttribute<Leitura, Integer> quantidadeSensores;
    public static volatile SingularAttribute<Leitura, String> descricao;

}