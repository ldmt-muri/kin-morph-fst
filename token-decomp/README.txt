Ulf Hermjakob (USC/ISI), Dec. 15, 2011:

Kinyarwanda verbs can be very complex, with many affixes, causing data 
fragmentation, bad word alignment and poor test translations (low BLEU).

At ISI, we conducted an experiment to see whether morphological
decomposition of verbs might improve MT. Decomposing all Kinyarwanda
tokens using the kin-single-decomp-v2.1.txt look-up table (described
below), our BLEU score improved by 0.8, which suggests that morphological
decomposition helps, even if imperfect and only limited to verbs.

In late November 2011, I built a look-up table for Kinyarwanda verb
decomposition, with 121,020 verb form entries, the result of running
my updated morphology scripts over all of our Kinyarwanda monolingual
and bilingual texts, resulting in these files:

data/Kinyarwanda/v2.1/token-decomp/kin-multi-decomp-v2.1.txt
data/Kinyarwanda/v2.1/token-decomp/kin-single-decomp-v2.1.txt

------------------------------------------------------------------------

The first file (kin-multi-decomp-v2.1.txt) is a lookup-up table
for Kinyarwanda verbs of the form
<surface-form> :: <decomposition> :: <raw-decomposition> :: <attributes>
A surface form can have multiple entries.

The <decomposition> has the structure
   {<prefix>- }*<stem>{ -<suffix>}
and is normalized with respect to phonetic assimilation, adds the
default -a suffix to the stem, and is generally geared towards use in MT.

<raw-decomposition> strictly follows the surface form.

Sample of typical <attributes>:
   subj:c2p : subject prefix for class 2 nouns (plural) "they"
   obj:c7s obj:2s,c15s : two object particles, the first for class 7 nouns
      (singular) "it", the second for second person singular (you) or
      class 15 nouns (singular) "it"
   future : future tense infix
   negative : negative prefix
   even : adverbial infix roughly corresponding to English "even"
   reflexive : reflexive infix ("oneself")
   there-on : PP suffix, roughly corresponding to English "there" or "on it"

Example:
ntibazanakikwiyerekeraho :: nti- ba- za- na- ki- ku- iyereka -ir -ho :: nti ba za na ki kw iyerek er a ho :: negative subj:c2p future even obj:c7s obj:2s,c15s there-on applicative
ntibazanakikwiyerekeraho :: nti- ba- za- na- ki- ku- i- erekera -ho :: nti ba za na ki kw iy ereker a ho :: negative subj:c2p future even obj:c7s obj:2s,c15s reflexive there-on
ntibazanakikwiyerekeraho :: nti- ba- za- na- ki- ku- i- ereka -ir -ho :: nti ba za na ki kw iy erek er a ho :: negative subj:c2p future even obj:c7s obj:2s,c15s reflexive there-on applicative
("they won't even show it to you for themselves on it")

------------------------------------------------------------------------

As some MT systems might not be able to accommodate lattices of tokens,
I also built a simple 1:1 look-up table, preferring decompositions with
fewer constituents over those with more (thus generally preferring
larger stems), and as a tie breaker, preferring decompositions with
more common sub-elements over those with less common sub-elements.

The second file (kin-single-decomp-v2.1.txt) is a look-up table
for Kinyarwanda verbs of the form
   <surface-word><tab>{<prefix>- }*<stem>{ -<suffix>}*

A few examples:
bitaga	ba- ita -aga
kora	kora
kore	kora -e
ndabyibuka	n- ra- bi- ibuka
yararokotse	ya- ra- rokotse
ntibazanakikwiyerekeraho	nti- ba- za- na- ki- ku- i- erekera -ho

------------------------------------------------------------------------

The files only cover verbs, and only verbs that can not also be
words with other parts of speech (with a few narrow exceptions),
and only verbs covered by the previously released Kinyarwanda
dictionary.
 
