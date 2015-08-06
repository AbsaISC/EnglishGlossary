package com.isc.absa.englishglossary.utility;

/**
 * Created by Absalom on 06/08/2015.
 */

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.content.SharedPreferences.Editor;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.ViewGroup;
        import android.widget.BaseExpandableListAdapter;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.disappster.autobus.Login;
        import com.disappster.autobus.Pasajes;
        import com.disappster.autobus.Tarifas;
        import com.disappster.autobus.db.dao.TurnoDAO;
        import com.disappster.autobus.db.dto.Turno;
        import com.zkc.pc700.R;

public class ListaExpandibleAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Ficha> fichas;
    private HashMap<Ficha, List<String>> impresion;
    ImpresionTickets printer = null;
    boolean isConnection=false;
    SharedPreferences sp = null;
    Editor edit;
    String header=null;
    String footer=null;
    String unidad=null;


    public ListaExpandibleAdapter(Context context, List<Ficha> fichas,
                                  HashMap<Ficha, List<String>> impresion) {
        this.context = context;
        this.fichas = fichas;
        this.impresion = impresion;
        sp= context.getSharedPreferences(Login.datos, context.MODE_PRIVATE);
        edit=sp.edit();

        header=sp.getString(Tarifas.KEY_HEADER, "");
        footer=sp.getString(Tarifas.KEY_FOOTER, "");
        unidad=sp.getString(Tarifas.KEY_UNIDAD, "");
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return this.fichas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        return this.impresion.get(fichas.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return this.fichas.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return impresion.get(this.fichas.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.list_ficha, null);
        }

        TextView tv_date = (TextView) convertView.findViewById(R.id.tv_string);
        TextView tv_hri = (TextView) convertView.findViewById(R.id.tv_horaI);
        TextView tv_hrf = (TextView) convertView.findViewById(R.id.tv_horaF);
        Button btn=(Button) convertView.findViewById(R.id.btn_id_imprimir);
        btn.setFocusable(false);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//				Toast.makeText(v.getContext(), "Lista "+groupPosition, 0).show();
                List<Turno> turnos=new ArrayList<Turno>();
                TurnoDAO tdao = new TurnoDAO(v.getContext());
                turnos = tdao.readFirst3();
                printer = new ImpresionTickets();
                if (!Settings.isDebug) {
                    if (!(isConnection = printer.abreConexion())) {
                        Toast.makeText(v.getContext(),
                                "No se establecio conexión", Toast.LENGTH_SHORT)
                                .show();
                        return;
                    }
                    printer.imprime(formaReporte(turnos.get(groupPosition)));
                    printer.cierraConexion();
                }else
                    Toast.makeText(v.getContext(), formaReporte(turnos.get(groupPosition)), Toast.LENGTH_LONG).show();
            }
        });

        tv_date.setText(this.fichas.get(groupPosition).getFecha());
        tv_hri.setText(this.fichas.get(groupPosition).getHrini());
        tv_hrf.setText(this.fichas.get(groupPosition).getHrfin());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_imp, null);
        }

        TextView tv = (TextView) convertView.findViewById(R.id.tv_info);
        tv.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return false;
    }

    private String formaReporte( Turno turno) {
        String resp="";
        resp+=header+"\n";
        resp+=("Reporte de Turno");
        resp+="\n"+("Unidad: " + unidad);// obtener unidad
        resp+="\n"+("Inicio: " + turno.getFechaIni() + " hr:" + turno.getHrIni());
        resp+="\n"+("Fin: " + turno.getFechaFin() + " hr:" + turno.getHrFin());
        resp+=("\n");
        resp+="\n"+("Tarifa  - #Boletos  - Monto");
        ArrayList<Integer> boletos = turno.getBoletosCompradosN2();
        ArrayList<Float> costos = turno.getTarifasNormales2();
        for (int i = 0; i < boletos.size(); i++) {
            resp+="\n"+("Tarifa" + (i + 1) + " -  " + boletos.get(i) + " -  $"+ costos.get(i)*boletos.get(i));
        }
        resp+=("\n");
        resp+="\n"+("Total pasajeros " + turno.getClientesTotales());
        resp+="\n"+("Monto $" + turno.getMontoTotal());
        resp+="\n"+footer;
        resp+=Settings.espacio;
        return resp;
    }
}