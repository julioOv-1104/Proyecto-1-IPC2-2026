import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CancelarUsuarios } from './cancelar-usuarios';

describe('CancelarUsuarios', () => {
  let component: CancelarUsuarios;
  let fixture: ComponentFixture<CancelarUsuarios>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CancelarUsuarios]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CancelarUsuarios);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
