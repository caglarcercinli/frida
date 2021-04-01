package be.vdab.frida.repositories;

import be.vdab.frida.domain.Saus;
import be.vdab.frida.exceptions.SausRepositoryException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Qualifier("properties")
public class PropertiesSausRepository implements SausRepository{
    //private static final Path PAD= Paths.get("/data/sauzen.properties");
    private final Path pad;

    public PropertiesSausRepository(@Value("${propertiesSauzenPad}") Path pad) {
        this.pad = pad;
    }


    @Override
    public List<Saus> findAll() {
        try{
            return Files.lines(pad).map(this::maakSaus).collect(Collectors.toList());
        } catch (IOException e) {
            throw new SausRepositoryException("fout bij lezen "+pad);
        }
    }
    private Saus maakSaus(String regel){
        var onderdelen=regel.split(":");
        if(onderdelen.length<2){
            throw new SausRepositoryException(pad+":"+regel+" : minder dan 2 onderdelen");
        }
        try {
            var naamEenIngredienten=onderdelen[1].split(",");
            var ingredienten= Arrays.copyOfRange(naamEenIngredienten,1,naamEenIngredienten.length);
            return new Saus(Long.parseLong(onderdelen[0]),naamEenIngredienten[0],ingredienten);
        } catch (NumberFormatException ex){
            throw new SausRepositoryException(pad+":"+regel+" :verkeerde id");
        }
    }
}
