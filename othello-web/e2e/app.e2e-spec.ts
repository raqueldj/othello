import { OthelloWebPage } from './app.po';

describe('othello-web App', () => {
  let page: OthelloWebPage;

  beforeEach(() => {
    page = new OthelloWebPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
