package PMP2_4.ws1718_a4.controller;

import java.util.List;

import PMP2_4.ws1718_a4.assets.Assets.AssetTyp;
import PMP2_4.ws1718_a4.basis.Asset;
import PMP2_4.ws1718_a4.basis.Boesewicht;
import PMP2_4.ws1718_a4.basis.Level;
import PMP2_4.ws1718_a4.basis.SpielZustand;
import PMP2_4.ws1718_a4.basis.Spielfigur;
import PMP2_4.ws1718_a4.basis.Zelle;
import PMP2_4.ws1718_a4.basis.Konstanten.Befehl;
import PMP2_4.ws1718_a4.basis.Konstanten.Richtung;

/**
 * Bekämpfen eines Gegners
 * 
 * @author Philipp Jenke
 *
 */
public class AktionBekaempfe extends RichtungsAktion {

  @Override
  public String verarbeite(List<Befehl> befehlskette) {

    if (befehlskette.size() != 2) {
      return "Ungültige Befehlssyntax für BEKAEMPFE.";
    }

    Befehl befehlGegner = befehlskette.get(0);
    if (befehlGegner != Befehl.GEGNER) {
      return "Ungültige Befehlssyntax für BEKAEMPFE.";
    }

    Befehl befehlRichtung = befehlskette.get(1);
    if (!richtungsBefehle.contains(befehlRichtung)) {
      return "Ungültiger Richtungsbefehl: " + befehlRichtung.toString();
    }

    /// Vorbereitung
    Richtung richtung = befehl2Richtung(befehlRichtung);
    Spielfigur spielfigur = SpielZustand.getInstance().getAktuellerLevel()
        .getSpielfigur();
    Zelle spielfigurZelle = spielfigur.getZelle();

    // Wand?
    if (spielfigurZelle.istWand(richtung)) {
      return "Eine Wand kann nicht angegeriffen werden!";
    }

    // Ziel-Zelle belegt?
    Zelle zielZelle = spielfigurZelle.getNachbarZelle(richtung);
    Asset asset = zielZelle.getAsset();
    if (asset == null) {
      return "Da ist niemand, der angegriffen werden kann!";
    } else if (asset.getTyp() == AssetTyp.BOESEWICHT) {
      Level level = SpielZustand.getInstance().getAktuellerLevel();
      level.removeBoesewicht((Boesewicht) asset);
      return "Glückwunsch: du hast den Bösewicht besiegt!";
    } else {
      return "Ein " + asset.getTyp().toString()
          + " kann nicht angegriffen werden.";
    }
  }
}
