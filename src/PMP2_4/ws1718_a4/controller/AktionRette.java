package PMP2_4.ws1718_a4.controller;

import java.util.List;

import PMP2_4.ws1718_a4.assets.Assets.AssetTyp;
import PMP2_4.ws1718_a4.basis.Asset;
import PMP2_4.ws1718_a4.basis.SpielZustand;
import PMP2_4.ws1718_a4.basis.Spielfigur;
import PMP2_4.ws1718_a4.basis.Zelle;
import PMP2_4.ws1718_a4.basis.Konstanten.Befehl;
import PMP2_4.ws1718_a4.basis.Konstanten.Richtung;
import PMP2_4.ws1718_a4.basis.Konstanten.SpielStatus;

public class AktionRette extends RichtungsAktion {

  @Override
  public String verarbeite(List<Befehl> befehlskette) {
    if (befehlskette.size() != 2) {
      return "Ungültige Befehlssyntax für RETTE.";
    }

    Befehl befehlGegner = befehlskette.get(0);
    if (befehlGegner != Befehl.FREUND) {
      return "Ungültige Befehlssyntax für RETTE.";
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
      return "Eine Wand kann nicht gerettet werden!";
    }

    // Ziel-Zelle belegt?
    Zelle zielZelle = spielfigurZelle.getNachbarZelle(richtung);
    Asset asset = zielZelle.getAsset();
    if (asset == null) {
      return "Da ist niemand, der gerettet werden kann!";
    } else if (asset.getTyp() == AssetTyp.BOESEWICHT) {
      SpielZustand.getInstance().setSpielStatus(SpielStatus.VERLOREN);
      return "Keine gute Idee, einen Boesewicht zu retten - du bist tot";
    } else if (asset.getTyp() == AssetTyp.NPC) {
      SpielZustand.getInstance().setSpielStatus(SpielStatus.GEWONNEN);
      return "Prima, du hast deinen Freund gerettet - Spiel gewonnen.";
    } else {
      return "Ein " + asset.getTyp().toString()
          + " kann nicht angegriffen werden.";
    }
  }

}
