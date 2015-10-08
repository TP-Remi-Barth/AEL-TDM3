package motif;

import static org.junit.Assert.*;

import org.junit.Test;

public class MotTest {

	@Test
	public void testIndiceMotifNaif() {

		Mot text = new Mot("le fermier est dans sa ferme et son fils 46 mange du caca");
		Mot motifPrefix = new Mot("le");
		Mot motifInterieur = new Mot("sa ferme");
		Mot motifSuffix = new Mot("du caca");
		
		Mot motifInexistant = new Mot("sdqsj osqdeaz ezae");
		Mot motifQuiCommenceBien = new Mot("dans sa ferme et sa fille");
		Mot motifDerniereLettreIncorrect = new Mot("dans sa ferma");
		Mot motifTropGrand = new Mot("le fermier est dans sa ferme et son fils 46 mange du cacas");

		assertEquals(text.indiceMotifNaif(motifPrefix), 0);
		assertEquals(text.indiceMotifNaif(motifInterieur), 20);
		assertEquals(text.indiceMotifNaif(motifSuffix), 50);

		assertEquals(text.indiceMotifNaif(motifInexistant), -1);
		assertEquals(text.indiceMotifNaif(motifQuiCommenceBien), -1);
		assertEquals(text.indiceMotifNaif(motifDerniereLettreIncorrect), -1);
		assertEquals(text.indiceMotifNaif(motifTropGrand), -1);
	}

	@Test
	public void testIndiceMotifAutomate() {
		
		Mot text = new Mot("lefermierestdanssafermeetsonfils");
		Mot motifPrefix = new Mot("le");
		Mot motifInterieur = new Mot("saferme");
		Mot motifSuffix = new Mot("fils");
		
		Mot motifInexistant = new Mot("sdqsjosqdeazezae");
		Mot motifQuiCommenceBien = new Mot("danssafermeetsafill");
		Mot motifDerniereLettreIncorrect = new Mot("danssaferma");
		Mot motifTropGrand = new Mot("lefermierestdanssafermeetsonfilsmangeducaca");

		assertEquals(text.indiceMotifAutomate(motifPrefix), 0);
		assertEquals(text.indiceMotifAutomate(motifInterieur), 16);
		assertEquals(text.indiceMotifAutomate(motifSuffix), 28);

		assertEquals(text.indiceMotifAutomate(motifInexistant), -1);
		assertEquals(text.indiceMotifAutomate(motifQuiCommenceBien), -1);
		assertEquals(text.indiceMotifAutomate(motifDerniereLettreIncorrect), -1);
		assertEquals(text.indiceMotifAutomate(motifTropGrand), -1);
	}
	@Test
	public void testIndiceMotifKMP() {		
		Mot text = new Mot("le fermier est dans sa ferme et son fils 46 mange du caca");
		Mot motifPrefix = new Mot("le");
		Mot motifInterieur = new Mot("sa ferme");
		Mot motifSuffix = new Mot("du caca");
		
		Mot motifInexistant = new Mot("sdqsj osqdeaz ezae");
		Mot motifQuiCommenceBien = new Mot("dans sa ferme et sa fille");
		Mot motifDerniereLettreIncorrect = new Mot("dans sa ferma");
		Mot motifTropGrand = new Mot("le fermier est dans sa ferme et son fils 46 mange du cacas");

		assertEquals(text.indiceMotifKMP(motifPrefix), 0);
		assertEquals(text.indiceMotifKMP(motifInterieur), 20);
		assertEquals(text.indiceMotifKMP(motifSuffix), 50);

		assertEquals(text.indiceMotifKMP(motifInexistant), -1);
		assertEquals(text.indiceMotifKMP(motifQuiCommenceBien), -1);
		assertEquals(text.indiceMotifKMP(motifDerniereLettreIncorrect), -1);
		assertEquals(text.indiceMotifKMP(motifTropGrand), -1);
	}
}
