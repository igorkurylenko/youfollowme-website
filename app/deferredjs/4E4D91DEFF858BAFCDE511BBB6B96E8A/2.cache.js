$wnd.app.runAsyncCallback2("function TV(a){this.a=a}\nfunction gW(a){this.a=a}\nfunction iW(a){this.a=a}\nfunction Nm(){this.b=new K7}\nfunction SV(a,b){l$(a.a,b)}\nfunction Lm(a,b){a.d=b;return a}\nfunction Dm(a){Bm(ocb,a);return Em(a)}\nfunction Cm(a){Bm('decodedURL',a);return encodeURI(a)}\nfunction Fm(a,b){if(a==null){throw new t_(b)}}\nfunction Gm(a,b){if(a==null||(m0(),a.length)==0){throw new t_(b)}}\nfunction nW(a){var b;if(!a.d){b=(wN(a.a),new cW);a.d=b}return a.d}\nfunction kW(a){var b;b=new S0;b.a+=Ycb;R0(b,mD(a));b.a+=$cb;return new YC(b.a)}\nfunction fW(a){this.c=new gW(this);this.d=new iW(this);this.e=a;this.a=ih($doc);this.b=new oD(this.a)}\nfunction cW(){SM();kT.call(this);this.a=(!this.a&&(this.a=ce()),this.a);GM(this,eW(new fW(this)))}\nfunction PV(a,b,c,d,e){UL();ZS.call(this,a,b,c,(SR(),PR));this.a=d;this.b=e;_p(this.s,692).Nc(this)}\nfunction Km(a,b){b!=null&&o0((m0(),b.substr(0,1)),'/')&&(b=y0(b,1,(m0(),b.length)-1));a.c=b;return a}\nfunction _Z(a){return Hm(Jm(Km(Im(Mm(new Nm,'https'),'twitter.com'),'search'),np(hp(uA,1),uab,2,5,[a])))}\nfunction bW(a,b){var c,d,e,f;c=Nk(b,zd(a.a));d=Ok(b,zd(a.a));e=_V(a,new AY(c,d));f=zd(a.a).style;f['cursor']=(e?(Eh(),wh):(Eh(),ph)).Fb()}\nfunction aW(a,b){var c,d,e,f;e=Nk(b,zd(a.a));f=Ok(b,zd(a.a));c=_V(a,new AY(e,f));!!c&&(d=_Z('#'+(_p(a.g,693),c).b),$wnd.open(d,'_blank',null),undefined)}\nfunction mW(a){var b,c,d;if(!a.c){d=new PV(aH($M(a.a)),(c=nW(a),c),lW(a),LR(oN(a.a)),(b=sQ(lN(a.a)),wN(a.a),b));ML((kN(a.a),d),zN(kN(a.a)));a.c=d}return a.c}\nfunction _V(a,b){var c,d,e,f,g;for(d=new y4(a.c);d.a<d.c.a.length;){c=_p(x4(d),144);e=_p(c.a[0],43);g=_p(c.a[1],76).b;f=new UY(e.c,g);if(NY(f,b))return _p(c.a[2],102)}return null}\nfunction eW(a){var b,c,d;c=new mF(kW(a.a).a);b=qD((wD(),c.o));nD(a.b);b.b?Mg(b.b,b.a,b.c):sD(b.a);kF(c,(d=a.e.a,Ed(d,(oZ(),Kdb)),Ld(d,a.c,(Qk(),Qk(),Pk)),Ld(d,a.d,($k(),$k(),Zk)),d),nD(a.b));return c}\nfunction Jm(a,b){Gm('q','Key cannot be null or empty');Fm(b,'Values cannot null. Try using removeParameter instead.');if(b.length==0){throw new t_('Values cannot be empty.  Try using removeParameter instead.')}H7(a.b,'q',b);return a}\nfunction Im(b,c){var d;if(c!=null&&(m0(),c.indexOf(':'))!=-1){d=t0(c,':',0);if(d.length>2){throw new t_('Host contains more than one colon: '+c)}try{Lm(b,m_(d[1]))}catch(a){a=SB(a);if(dq(a,29)){throw new t_('Could not parse port out of host: '+c)}else throw RB(a)}c=d[0]}b.a=c;return b}\nfunction Mm(a,b){var c,d,e;Fm(b,'Protocol cannot be null');d=(m0(),'://'.length);o0(y0(b,b.length-d,d),'://')?(b=w0(b,0,b.length-3)):(e=':/'.length,o0(y0(b,b.length-e,e),':/')?(b=w0(b,0,b.length-2)):(c=':'.length,o0(y0(b,b.length-c,c),':')&&(b=w0(b,0,b.length-1))));if(b.indexOf(':')!=-1){throw new t_('Invalid protocol: '+b)}Gm(b,'Protocol cannot be empty');a.e=b;return a}\nfunction Hm(a){var b,c,d,e,f,g,h,i;e=new S0;R0(R0(e,Cm(a.e)),'://');a.a!=null&&R0(e,Cm(a.a));a.d!=sab&&P0((e.a+=':',e),a.d);a.c!=null&&!o0('',a.c)&&R0((e.a+='/',e),q0(q0(Cm(a.c),'?','%3F'),'#','%23'));d=63;for(c=new a8(new X7(a.b));c.b!=c.c.a.b;){b=_7(c);for(g=_p(b.e,21),h=0,i=g.length;h<i;++h){f=g[h];N0(R0((e.a+=String.fromCharCode(d),e),Dm(bq(b.d))),61);f!=null&&R0(e,(Bm(ocb,f),Em(f)));d=38}}return e.a}\nwC(591,1,{},Nm);_.a=null;_.c=null;_.d=sab;_.e='http';var pt=W$(vbb,'UrlBuilder',591);wC(375,162,{145:1,20:1,11:1,13:1,51:1,30:1,693:1},PV);_.md=function QV(){var a,b,c;b=new m$;c=(a=nO(this.b),ZZ(a,(cZ(),ZY)));sR(new tR(this.a,new TV(b)),c.b!=null?c.b:''+c.c);return b};_.Pc=function RV(){XS(this)};var Iy=W$(ndb,'HashtagTrendsPresenter',375);wC(376,1,{},TV);_.xb=function VV(a){SV(this,_p(a,37))};_.zb=function UV(a){k$(this.a)};var Fy=W$(ndb,'HashtagTrendsPresenter/1',376);wC(429,165,{13:1,93:1,692:1},cW);_.td=function dW(){return !this.a&&(this.a=ce()),this.a};var My=W$(ndb,'HashtagTrendsView',429);wC(507,1,{},fW);var Ly=W$(ndb,'HashtagTrendsView_BinderImpl/Widgets',507);wC(508,1,edb,gW);_.Kb=function hW(a){aW(this.a.e,a)};var Jy=W$(ndb,'HashtagTrendsView_BinderImpl/Widgets/1',508);wC(509,1,Ldb,iW);_.Lb=function jW(a){bW(this.a.e,a)};var Ky=W$(ndb,'HashtagTrendsView_BinderImpl/Widgets/2',509);wC(349,1,kdb);_.Ab=function tW(){this.b.xb(mW(this.a.a))};iab(rf)(2);\n//# sourceURL=app-2.js\n")
