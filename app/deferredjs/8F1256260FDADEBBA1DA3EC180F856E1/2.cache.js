$wnd.app.runAsyncCallback2("function PV(a){this.a=a}\nfunction cW(a){this.a=a}\nfunction eW(a){this.a=a}\nfunction Jm(){this.b=new E7}\nfunction OV(a,b){f$(a.a,b)}\nfunction Hm(a,b){a.d=b;return a}\nfunction zm(a){xm(hcb,a);return Am(a)}\nfunction ym(a){xm('decodedURL',a);return encodeURI(a)}\nfunction Bm(a,b){if(a==null){throw new n_(b)}}\nfunction Cm(a,b){if(a==null||(g0(),a.length)==0){throw new n_(b)}}\nfunction jW(a){var b;if(!a.d){b=(sN(a.a),new $V);a.d=b}return a.d}\nfunction gW(a){var b;b=new M0;b.a+=Rcb;L0(b,hD(a));b.a+=Tcb;return new TC(b.a)}\nfunction bW(a){this.c=new cW(this);this.d=new eW(this);this.e=a;this.a=eh($doc);this.b=new jD(this.a)}\nfunction $V(){OM();gT.call(this);this.a=(!this.a&&(this.a=_d()),this.a);CM(this,aW(new bW(this)))}\nfunction LV(a,b,c,d,e){QL();VS.call(this,a,b,c,(OR(),LR));this.a=d;this.b=e;Xp(this.s,691).Nc(this)}\nfunction Gm(a,b){b!=null&&i0((g0(),b.substr(0,1)),'/')&&(b=s0(b,1,(g0(),b.length)-1));a.c=b;return a}\nfunction VZ(a){return Dm(Fm(Gm(Em(Im(new Jm,'https'),'twitter.com'),'search'),jp(dp(pA,1),oab,2,5,[a])))}\nfunction ZV(a,b){var c,d,e,f;c=Jk(b,zd(a.a));d=Kk(b,zd(a.a));e=XV(a,new wY(c,d));f=zd(a.a).style;f['cursor']=(e?(Ah(),sh):(Ah(),lh)).Fb()}\nfunction YV(a,b){var c,d,e,f;e=Jk(b,zd(a.a));f=Kk(b,zd(a.a));c=XV(a,new wY(e,f));!!c&&(d=VZ('#'+(Xp(a.g,692),c).b),$wnd.open(d,'_blank',null),undefined)}\nfunction iW(a){var b,c,d;if(!a.c){d=new LV(YG(WM(a.a)),(c=jW(a),c),hW(a),HR(kN(a.a)),(b=oQ(hN(a.a)),sN(a.a),b));IL((gN(a.a),d),vN(gN(a.a)));a.c=d}return a.c}\nfunction XV(a,b){var c,d,e,f,g;for(d=new s4(a.c);d.a<d.c.a.length;){c=Xp(r4(d),144);e=Xp(c.a[0],45);g=Xp(c.a[1],76).b;f=new PY(e.c,g);if(JY(f,b))return Xp(c.a[2],102)}return null}\nfunction aW(a){var b,c,d;c=new mF(gW(a.a).a);b=lD((rD(),c.o));iD(a.b);b.b?Ig(b.b,b.a,b.c):nD(b.a);kF(c,(d=a.e.a,Ed(d,(jZ(),Ddb)),Ld(d,a.c,(Mk(),Mk(),Lk)),Ld(d,a.d,(Wk(),Wk(),Vk)),d),iD(a.b));return c}\nfunction Fm(a,b){Cm('q','Key cannot be null or empty');Bm(b,'Values cannot null. Try using removeParameter instead.');if(b.length==0){throw new n_('Values cannot be empty.  Try using removeParameter instead.')}B7(a.b,'q',b);return a}\nfunction Em(b,c){var d;if(c!=null&&(g0(),c.indexOf(':'))!=-1){d=n0(c,':',0);if(d.length>2){throw new n_('Host contains more than one colon: '+c)}try{Hm(b,g_(d[1]))}catch(a){a=NB(a);if(_p(a,29)){throw new n_('Could not parse port out of host: '+c)}else throw MB(a)}c=d[0]}b.a=c;return b}\nfunction Im(a,b){var c,d,e;Bm(b,'Protocol cannot be null');d=(g0(),'://'.length);i0(s0(b,b.length-d,d),'://')?(b=q0(b,0,b.length-3)):(e=':/'.length,i0(s0(b,b.length-e,e),':/')?(b=q0(b,0,b.length-2)):(c=':'.length,i0(s0(b,b.length-c,c),':')&&(b=q0(b,0,b.length-1))));if(b.indexOf(':')!=-1){throw new n_('Invalid protocol: '+b)}Cm(b,'Protocol cannot be empty');a.e=b;return a}\nfunction Dm(a){var b,c,d,e,f,g,h,i;e=new M0;L0(L0(e,ym(a.e)),'://');a.a!=null&&L0(e,ym(a.a));a.d!=mab&&J0((e.a+=':',e),a.d);a.c!=null&&!i0('',a.c)&&L0((e.a+='/',e),k0(k0(ym(a.c),'?','%3F'),'#','%23'));d=63;for(c=new W7(new R7(a.b));c.b!=c.c.a.b;){b=V7(c);for(g=Xp(b.e,21),h=0,i=g.length;h<i;++h){f=g[h];H0(L0((e.a+=String.fromCharCode(d),e),zm(Zp(b.d))),61);f!=null&&L0(e,(xm(hcb,f),Am(f)));d=38}}return e.a}\nrC(591,1,{},Jm);_.a=null;_.c=null;_.d=mab;_.e='http';var lt=Q$(pbb,'UrlBuilder',591);rC(375,162,{145:1,20:1,11:1,13:1,51:1,30:1,692:1},LV);_.md=function MV(){var a,b,c;b=new g$;c=(a=jO(this.b),TZ(a,(ZY(),UY)));oR(new pR(this.a,new PV(b)),c.b!=null?c.b:''+c.c);return b};_.Pc=function NV(){TS(this)};var Dy=Q$(gdb,'HashtagTrendsPresenter',375);rC(376,1,{},PV);_.xb=function RV(a){OV(this,Xp(a,37))};_.zb=function QV(a){e$(this.a)};var Ay=Q$(gdb,'HashtagTrendsPresenter/1',376);rC(429,165,{13:1,93:1,691:1},$V);_.td=function _V(){return !this.a&&(this.a=_d()),this.a};var Hy=Q$(gdb,'HashtagTrendsView',429);rC(509,1,{},bW);var Gy=Q$(gdb,'HashtagTrendsView_BinderImpl/Widgets',509);rC(510,1,Zcb,cW);_.Kb=function dW(a){YV(this.a.e,a)};var Ey=Q$(gdb,'HashtagTrendsView_BinderImpl/Widgets/1',510);rC(511,1,Edb,eW);_.Lb=function fW(a){ZV(this.a.e,a)};var Fy=Q$(gdb,'HashtagTrendsView_BinderImpl/Widgets/2',511);rC(349,1,ddb);_.Ab=function pW(){this.b.xb(iW(this.a.a))};cab(nf)(2);\n//# sourceURL=app-2.js\n")
