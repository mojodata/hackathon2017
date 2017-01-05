import { MojoMapPage } from './app.po';

describe('mojo-map App', function() {
  let page: MojoMapPage;

  beforeEach(() => {
    page = new MojoMapPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
