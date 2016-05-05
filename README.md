Saving Water Tips - Dicas Para Economia de Agua.

Nesse projeto android, eu busquei mostrar informacoes uteis que podem ajudar o usuario a economizar agua dentro de casa.

O programa comeca com uma breve introducao apresentando alguns fatos do por que eh importante economizar agua. Logo apos, o usuario eh redirecionado para a tela principal aonde pode escolher dentro de um GridView, 15 diferentes dicas.

Quando uma dessas opcoes eh selecionada, uma nova activity eh lancada, DetailsActivity, mostrando uma foto relacionada com a dica escolhida e o seu respectivo texto.

O App pode ser dividido em 3 principais Telas, MainActivity, DetailsActivity e IntroductionActivity. 

A IntroductionActivity, eh composta por um par de botoes no bottom do layout, que permitem a navegacao do ViewPager que contem as informacoes da introducao.

Na MainActivity, um GridView eh apresentado ao usuario, aonde ele pode escolher entre as dicas que ele quer ver. Caso um item desse grid seja selecionado, a DetailsACtivity sera lancada mostrando os detalhes para esse item selecionado. Tambem na MainActivity, existe um  NavigationDrawer que caso o usuario deseje, ele pode lancar a IntroductionActivity por ele.

A DetailsActivity, apresenta uma imagem e um texto relacionados ao item selecionado na MainActivity. Tambem um FloatingActionButton que permite o usuario compartilhar o texto que ele leu.
