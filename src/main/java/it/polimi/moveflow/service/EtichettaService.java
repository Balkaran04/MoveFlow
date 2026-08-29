package it.polimi.moveflow.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import it.polimi.moveflow.etichetta.Etichetta;
import it.polimi.moveflow.etichetta.GeneratoreEtichetta;
import it.polimi.moveflow.etichetta.GeneratoreEtichettaStandard;
import it.polimi.moveflow.model.Materiale;
import it.polimi.moveflow.repository.MaterialeRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
@Service
public class EtichettaService {

    private final MaterialeRepository materialeRepository;

    public EtichettaService(MaterialeRepository materialeRepository) {
        this.materialeRepository = materialeRepository;
    }

    public String generaEtichetta(Long idMateriale){
        Optional<Materiale> m = materialeRepository.findById(idMateriale);

        if(m.isEmpty()){
            throw new IllegalArgumentException("Non esiste il Materiale");

        }
        Materiale m1 = m.get();

        GeneratoreEtichetta g = new GeneratoreEtichettaStandard();
        Etichetta e = g.creaEtichetta(m1);

        return e.generaTesto();

    }



    public byte[] generaPdfEtichetta(Long idMateriale) throws IOException {

        Optional<Materiale> m = materialeRepository.findById(idMateriale);

        if(m.isEmpty()){
            throw new IllegalArgumentException("Non esiste il Materiale");

        }
        Materiale m1 = m.get();

        GeneratoreEtichetta g= new GeneratoreEtichettaStandard();
        Etichetta e= g.creaEtichetta(m1);
        String testo = e.generaTesto();
        return creaPdf(testo, m1.getId());
    }


    private byte[] creaPdf(String testo, Long idMateriale) throws IOException{
        String barcode = "MAT|" + idMateriale;
        Code128Writer writer = new Code128Writer();

        BitMatrix matrix = writer.encode(
                barcode,
                BarcodeFormat.CODE_128,
                220,
                50

        );

        ByteArrayOutputStream barcodeOut = new ByteArrayOutputStream();

        MatrixToImageWriter.writeToStream(
                matrix,
                "PNG",
                barcodeOut
        );

        PDDocument documento = new PDDocument();
        float larghezza = 100 * 72f / 25.4f;
        float altezza = 60 * 72f / 25.4f;

        PDPage pagina = new PDPage(new PDRectangle(larghezza,altezza));
        documento.addPage(pagina);

        PDPageContentStream stream = new PDPageContentStream(documento,pagina);
        stream.beginText();
        stream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA),12);

        stream.newLineAtOffset(20,altezza -25 );
        stream.setLeading(18);

        for(String riga: testo.split("\n")){
            stream.showText(riga);
            stream.newLine();
        }
        stream.endText();
        PDImageXObject immagineBarcode =
                PDImageXObject.createFromByteArray(
                        documento,
                        barcodeOut.toByteArray(),
                        "barcode"
                );
        stream.drawImage(
                immagineBarcode,
                30,
                8,
                220,
                35
        );

        stream.close();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        documento.save(out);
        documento.close();
        return out.toByteArray();

    }


}
