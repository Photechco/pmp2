package PMP2_4.ws1718_a4.controller;

import java.util.List;

import PMP2_4.ws1718_a4.basis.Asset;
import PMP2_4.ws1718_a4.basis.SpielZustand;
import PMP2_4.ws1718_a4.basis.Spielfigur;
import PMP2_4.ws1718_a4.basis.Zelle;
import PMP2_4.ws1718_a4.basis.Konstanten.Befehl;
import PMP2_4.ws1718_a4.basis.Konstanten.Richtung;
import PMP2_4.ws1718_a4.basis.Konstanten.SpielStatus;

/**
 * Aktion für die Bewegung in eine Richtung.
 * 
 * @author Philipp Jenke
 *
 */
public class AktionGehe extends RichtungsAktion {

  @Override
  public String verarbeite(List<Befehl> befehlskette) {
    if (befehlskette.size() != 1) {
      return "Ungültige Befehlskette.";
    }

    Befehl befehl = befehlskette.get(0);
    if (!richtungsBefehle.contains(befehl)) {
      return "Ungültiger Richtungsbefehl: " + befehl.toString();
    }

    // Bewegung in die angegebene Richtung
    Richtung richtung = befehl2Richtung(befehl);
    Spielfigur spielfigur = SpielZustand.getInstance().getAktuellerLevel()
        .getSpielfigur();
    Zelle spielfigurZelle = spielfigur.getZelle();

    // Wand?
    if (spielfigurZelle.istWand(richtung)) {
      return "Da ist eine Wand!";
    }

    // Ziel-Zelle belegt?
    Zelle zielZelle = spielfigurZelle.getNachbarZelle(richtung);
    Asset asset = zielZelle.getAsset();
    if (asset != null) {
      switch (asset.getTyp()) {
      case BOESEWICHT:
        SpielZustand.getInstance().setSpielStatus(SpielStatus.VERLOREN);
        return "Vom Boesewicht gefressen - Game over!";
      default:
        return "Hier steht doch schon jemand!!";
      }
    }
    spielfigur.setZelle(zielZelle);

    return "Bewege Spielfigur in Richtung " + befehl.toString();
  }

}
