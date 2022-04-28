package com.juanpabloprado.calculator;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route("")
public class MainView extends VerticalLayout {

    public MainView(Calculator calculator) {

        VerticalLayout todosList = new VerticalLayout();
        TextField inputField = new TextField();
        Button addButton = new Button("=");
        addButton.addClickListener(click -> {
            String result = inputField.getValue() + " = ";

            // https://stackoverflow.com/a/13525053
            String[] split = inputField.getValue().split("(?<=[-+*/()])|(?=[-+*/()])");
            String[] strings = StringUtils.stripAll(split);
            List<String> tokens = Arrays.stream(strings).filter(s -> !s.isBlank()).collect(Collectors.toList());
            double r = calculator.processInput(tokens);

            Checkbox checkbox = new Checkbox(result + r);

            todosList.add(checkbox);
        });
        addButton.addClickShortcut(Key.ENTER);

        add(
                new H1("Calculator"),
                todosList,
                new HorizontalLayout(
                        inputField,
                        addButton
                )
        );
    }
}
